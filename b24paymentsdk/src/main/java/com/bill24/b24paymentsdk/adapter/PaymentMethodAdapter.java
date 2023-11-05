package com.bill24.b24paymentsdk.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.bill24.b24paymentsdk.R;

import com.bill24.b24paymentsdk.core.RetrofitClient;
import com.bill24.b24paymentsdk.customShapeDrawable.CustomShape;
import com.bill24.b24paymentsdk.customShapeDrawable.SelectedState;
import com.bill24.b24paymentsdk.helper.ConvertColorHexa;
import com.bill24.b24paymentsdk.helper.SetFont;
import com.bill24.b24paymentsdk.helper.StickyHeaderItemDecoration;
import com.bill24.b24paymentsdk.helper.Translate;
import com.bill24.b24paymentsdk.helper.translateLanguage.TranslateLanguage;
import com.bill24.b24paymentsdk.model.AddToFavoriteModel;
import com.bill24.b24paymentsdk.model.BankPaymentMethodItemModel;
import com.bill24.b24paymentsdk.model.BankPaymentMethodModel;
import com.bill24.b24paymentsdk.model.CheckoutPageConfigModel;
import com.bill24.b24paymentsdk.model.TransactionInfoModel;
import com.bill24.b24paymentsdk.model.baseResponseModel.BaseResponse;
import com.bill24.b24paymentsdk.model.conts.Constant;
import com.bill24.b24paymentsdk.model.conts.LanguageCode;
import com.bill24.b24paymentsdk.model.requestModel.AddToFavoriteRequestModel;
import com.bill24.b24paymentsdk.theme.CustomTheme;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentMethodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements StickyHeaderItemDecoration.StickyHeaderInterface {

    private static final int VIEW_HEADER_TYPE=0;
    private static final int VIEW_ITEM_TYPE=1;
    private CheckoutPageConfigModel checkoutPageConfigModel;

    private TransactionInfoModel transactionInfoModel;
    private List<BankPaymentMethodModel> bankPaymentMethodModelList;
    private PaymentMethodClickListener listener;

    private LinearLayoutCompat sectionContainer;
    private AppCompatTextView textSectionHeader,
            textBankName;
    private String tranasctionId, refererKey,language,baseUrl;
    private boolean isLightMode;

   public void setPaymentMethod(
           CheckoutPageConfigModel checkoutPageConfigModel,
           TransactionInfoModel transactionInfoModel,
           List<BankPaymentMethodModel> bankPaymentMethodModelList,
           String transactionId,
           String refererKey,
           boolean isLightMode,
           String language,
           String baseUrl){
       this.checkoutPageConfigModel=checkoutPageConfigModel;
       this.transactionInfoModel=transactionInfoModel;
       this.bankPaymentMethodModelList=bankPaymentMethodModelList;
       this.tranasctionId=transactionId;
       this.refererKey=refererKey;
       this.isLightMode=isLightMode;
       this.language=language;
       this.baseUrl=baseUrl;

       notifyDataSetChanged();
   }
    public void setOnItemClickListener(PaymentMethodClickListener listener){
        this.listener=listener;
    }

    private void initItemView(View view){
        textBankName=view.findViewById(R.id.text_bank_name);
    }

    private void updateHeaderFont(Context context,AppCompatTextView textSectionHeader){
        SetFont font=new SetFont();
        Typeface typeface=font.setFont(context, language);
        textSectionHeader.setTypeface(typeface);
        textSectionHeader.setTextSize(11);
        textSectionHeader.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        textSectionHeader.setTextColor(context.getColor(R.color.header_font_color));

    }

    private void applyHeaderTheme(LinearLayoutCompat headerContainer,AppCompatTextView sectionHeader){
        CustomTheme customTheme=CustomTheme.getThemeFromAPI(isLightMode,checkoutPageConfigModel);

        headerContainer.setBackgroundColor(Color.parseColor(customTheme.getLabelBackgroundColor()));
        sectionHeader.setTextColor(Color.parseColor(customTheme.getLabelTextColor()));

    }

    private void updateItemFont(Context context){
//      SetFont font=new SetFont();
        Typeface bankNameTypeface= ResourcesCompat.getFont(context, R.font.roboto_medium);
        textBankName.setTypeface(bankNameTypeface);
        textBankName.setTextSize(12);
        textBankName.setPaintFlags(Paint.FAKE_BOLD_TEXT_FLAG);

    }

    private void postAddToFavorite(String bankId,boolean isFavorite){
        AddToFavoriteRequestModel model=new AddToFavoriteRequestModel(tranasctionId,bankId,isFavorite);
        Call<BaseResponse<AddToFavoriteModel>> call= RetrofitClient.getInstance(baseUrl).
                getApiClient().postAddToFavorite(Constant.CONTENT_TYPE,Constant.TOKEN,refererKey,model);

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse<AddToFavoriteModel>> call, @NonNull Response<BaseResponse<AddToFavoriteModel>> response) {
                // response.body();
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse<AddToFavoriteModel>> call, @NonNull Throwable t) {

            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        if (viewType==VIEW_HEADER_TYPE){
            View view=inflater.inflate(R.layout.section_header_layout,parent,false);
            textSectionHeader=view.findViewById(R.id.text_section_header);
            sectionContainer=view.findViewById(R.id.section_header_container);

            //apply header theme

            applyHeaderTheme(sectionContainer,textSectionHeader);

            //Update Font
            updateHeaderFont(view.getContext(),textSectionHeader);


            return new SectionViewHolder(view);
        }else {
            View view=inflater.inflate(R.layout.card_payment_method_item_layout,parent,false);
            initItemView(view);
            //Update Font
            updateItemFont(view.getContext());

            //Update Image Favorite
            return new BankItemViewHolder(view);
        }

    }
    @Override
    public int getItemViewType(int position) {
        int currentPos = 0;

        for (BankPaymentMethodModel section : bankPaymentMethodModelList) {
            // Check if the current position corresponds to the section itself
            if (currentPos == position) {
                return VIEW_HEADER_TYPE;
            }
            // Increment the position to account for the section
            currentPos++;
            // Check if the current position corresponds to an item within the section
            if (section.getItems() != null) {
                for (BankPaymentMethodItemModel item : section.getItems()) {
                    if (currentPos == position) {
                        return VIEW_ITEM_TYPE;
                    }
                    currentPos++;
                }
            }
        }
        throw new IllegalArgumentException("InValid Position");
           }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int currentPos = 0;
        for (BankPaymentMethodModel section : bankPaymentMethodModelList) {
            if (currentPos == position) {
                // Bind data for section
                ((SectionViewHolder) holder).bindSection(section);

               // applyHeaderStyle(((SectionViewHolder) holder).headerContainer);
                return; // Exit the method after binding the section
            }
            currentPos++;

            if (section.getItems() != null) {
                for (BankPaymentMethodItemModel item : section.getItems()) {
                    if (currentPos == position) {
                        // Bind data for item
                        ((BankItemViewHolder) holder).bindItem(item);

                        //Set Item Click Event
                        holder.itemView.setOnClickListener(v->{
                            //Toast.makeText(v.getContext(), ""+item.getId(), Toast.LENGTH_SHORT).show();
                            listener.OnItemPaymentMethodClick(item);
                        });

                        //Set Favorite Click
                        ((BankItemViewHolder)holder).addToFavoriteContainer.setOnClickListener(v->{
                            item.setFavorite(!item.isFavorite());

                            boolean isFav=item.isFavorite();

                            if(isFav){
                                //Toast.makeText(v.getContext(), ""+isFav, Toast.LENGTH_SHORT).show();
                                 postAddToFavorite(item.getId(),isFav);
                                ((BankItemViewHolder)holder).imageFavIcon.setImageResource(R.drawable.favorite_icon);

                            }else {
                                //Toast.makeText(v.getContext(), ""+isFav, Toast.LENGTH_SHORT).show();
                                postAddToFavorite(item.getId(),isFav);
                                ((BankItemViewHolder)holder).imageFavIcon.setImageResource(R.drawable.un_favorite_icon);
                            }

                        });

                        return; // Exit the method after binding the item
                    }
                    currentPos++;
                }
            }
        }
   }

    @Override
    public int getItemCount()
    {
        int count=0;
        for(BankPaymentMethodModel section:bankPaymentMethodModelList){
            count++;
            if(section.getSection()!=null){
                count+=section.getItems().size();
            }
        }
        return count;
    }

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
       int headerPosition=0;
        do{
            if(this.isHeader(itemPosition)){
                headerPosition=itemPosition;
                break;
            }
            itemPosition-=1;
        }while (itemPosition>=0);
        return headerPosition;
    }

    @Override
    public boolean isHeader(int itemPosition) {
        int currentPos = 0;

        for (BankPaymentMethodModel section : bankPaymentMethodModelList) {
            if (itemPosition == currentPos) {
                return true; // This item is a section header
            }
            currentPos++; // Move to the next position
            if (section.getItems() != null) {
                // Check if the current itemPosition is within the range of this section's items
                if (itemPosition < currentPos + section.getItems().size()) {
                    return false; // This item is not a section header
                }
                // Increment currentPos to move to the next section
                currentPos += section.getItems().size();
            }
        }
        return false;

    }

    @Override
    public int getHeaderLayout(int headerPosition) {
       return R.layout.section_header_layout;
    }

    @Override
    public void bindHeaderData(View header, int headerPosition) {
        int currentPos = 0;
        LinearLayoutCompat sectionHeader=header.findViewById(R.id.section_header_container);
        AppCompatTextView textSectionHeader = header.findViewById(R.id.text_section_header);
        for (BankPaymentMethodModel section : bankPaymentMethodModelList) {
            if (currentPos == headerPosition) {
                if (language.equals(LanguageCode.EN)) {
                    textSectionHeader.setText(section.getSection());
                } else {
                    textSectionHeader.setText(section.getSectionKh());
                }
                updateHeaderFont(header.getContext(),textSectionHeader);


                //apply header theme
                applyHeaderTheme(sectionHeader,textSectionHeader);



                return;
            }
            currentPos++; // Move to the next position
            if (section.getItems() != null) {
                int itemCount = section.getItems().size();
                if (headerPosition < currentPos + itemCount) {
                    // This is not a header; it's within the items of the current section
                    return;
                }
                currentPos += itemCount;
            }
        }

    }


    public class SectionViewHolder extends RecyclerView.ViewHolder {

        private LinearLayoutCompat headerContainer;
        private AppCompatTextView textSection;
        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            textSection=itemView.findViewById(R.id.text_section_header);
            headerContainer=itemView.findViewById(R.id.section_header_container);

            applyHeaderTheme();
        }

        void applyHeaderTheme(){
            CustomTheme customTheme=CustomTheme.getThemeFromAPI(isLightMode,checkoutPageConfigModel);

            headerContainer.setBackgroundColor(Color.parseColor(customTheme.getLabelBackgroundColor()));
            textSection.setTextColor(Color.parseColor(customTheme.getLabelTextColor()));
        }

        void bindSection(BankPaymentMethodModel section){
            if(language.equals(LanguageCode.EN)){
                textSection.setText(section.getSection());
            }else {
                textSection.setText(section.getSectionKh());
            }
        }
    }

   public  class  BankItemViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView textBankName,textBankServicePayment_Amount,
            textSemicolon,textBankFee;
        private AppCompatImageView imageFavIcon, imageBankIcon;
        private FrameLayout addToFavoriteContainer,containerBankItemFirstLayer,
                containerBankItemSecondLayer;
        private LinearLayoutCompat containerFee;
        private CardView bankImageBackground;

        public BankItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textBankName=itemView.findViewById(R.id.text_bank_name);
            textBankServicePayment_Amount=itemView.findViewById(R.id.text_payment_service_amount);
            imageFavIcon=itemView.findViewById(R.id.image_favorite_icon);
            imageBankIcon =itemView.findViewById(R.id.image_bank_icon);
            addToFavoriteContainer=itemView.findViewById(R.id.add_to_favorite_container);
            containerFee=itemView.findViewById(R.id.container_fee);
            textSemicolon=itemView.findViewById(R.id.text_semicolon);
            textBankFee=itemView.findViewById(R.id.text_payment_service_title);
            containerBankItemFirstLayer=itemView.findViewById(R.id.container_bank_item_first_layer);
            containerBankItemSecondLayer=itemView.findViewById(R.id.container_bank_item_second_layer);
            bankImageBackground=itemView.findViewById(R.id.bank_image_background);



            //CUSTOM SHAPE BANK ICON
            ShapeDrawable shape=CustomShape.applyShape(Color.TRANSPARENT,8,itemView.getContext());
            bankImageBackground.setBackground(shape);

            applyItemTheme(itemView.getContext());
        }

        void applyItemTheme(Context context){
            CustomTheme customTheme=CustomTheme.getThemeFromAPI(isLightMode,checkoutPageConfigModel);
            ShapeDrawable bankItemShape=CustomShape.applyShape(Color.parseColor(
                    customTheme.getBankButtonBackgroundColor()),12,context);

            String selectorColor=ConvertColorHexa.getFiftyPercentColor(customTheme.getBankButtonBackgroundColor());
            ShapeDrawable bankItemSelected=CustomShape.applyShape(Color.parseColor(
                   selectorColor ),12,context
            );

            ShapeDrawable normal=new ShapeDrawable();
            normal.getPaint().setColor(Color.TRANSPARENT);

            StateListDrawable selector = new StateListDrawable();
            selector.addState(new int[]{android.R.attr.state_pressed}, bankItemSelected); // Selected state
            selector.addState(new int[]{}, normal);
            containerBankItemSecondLayer.setBackground(selector);

            int elevation=(int)(5*context.getResources().getDisplayMetrics().density);
            containerBankItemFirstLayer.setElevation(elevation);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                int shadow =Color.argb(36,183,190,203);
                int shadowPX = (int) (shadow * context.getResources().getDisplayMetrics().density);
                containerBankItemFirstLayer.setOutlineSpotShadowColor(shadowPX);
            }
            containerBankItemFirstLayer.setBackground(bankItemShape);


            //bank name
            textBankName.setTextColor(Color.parseColor(
                    customTheme.getBankButtonTextPrimaryColor()));

            textBankFee.setTextColor(Color.parseColor(
                    customTheme.getBanButtonTextSecondaryColor()));
            textBankServicePayment_Amount.setTextColor(Color.parseColor(
                    customTheme.getBanButtonTextSecondaryColor()));

            textSemicolon.setTextColor(Color.parseColor(customTheme.getBanButtonTextSecondaryColor()));

            //favorite shape and theme
            ShapeDrawable favButtonShape=CustomShape.applyShape(
                    Color.parseColor(customTheme.getFavoriteButtonBackgroundColor()),
                            6,context);
            String favBtnSelectorColor=ConvertColorHexa.getFiftyPercentColor(
                    customTheme.getFavoriteButtonBackgroundColor()
            );
            ShapeDrawable favBtnSelector=CustomShape.applyShape(
                    Color.parseColor(favBtnSelectorColor),
                    6,context);

            StateListDrawable favSelector=SelectedState.selectedSate(favButtonShape,favBtnSelector);
            addToFavoriteContainer.setBackground(favSelector);

            //favorite icon
            ColorFilter colorFilter=new PorterDuffColorFilter(
                    Color.parseColor(customTheme.getFavoriteButtonTextColor()),PorterDuff.Mode.SRC_ATOP);
            imageFavIcon.setColorFilter(colorFilter);


        }
        @SuppressLint("SetTextI18n")
        void bindItem(BankPaymentMethodItemModel bankPaymentMethodItemModel){

            if(language.equals(LanguageCode.EN)){
                textBankName.setText(bankPaymentMethodItemModel.getName());
                textBankFee.setText(Translate.FEE_EN);
            }else {
                textBankFee.setText(Translate.FEE_KM);
                if(bankPaymentMethodItemModel.getNameKh().equals("")){
                    textBankName.setText(bankPaymentMethodItemModel.getName());
                }else {
                    textBankName.setText(bankPaymentMethodItemModel.getNameKh());
                }
            }

            textBankServicePayment_Amount.setText(bankPaymentMethodItemModel.getFeeDisplay());

            //load image from internet
            Picasso.get().load(bankPaymentMethodItemModel.getLogo())
                    .placeholder(R.drawable.placeholder_image)
                    .into(imageBankIcon) ;

            if(transactionInfoModel.isAllowFavorite()){
                addToFavoriteContainer.setVisibility(View.VISIBLE);
                if(bankPaymentMethodItemModel.isFavorite()){
                    imageFavIcon.setImageResource(R.drawable.favorite_icon);
                }else {
                    imageFavIcon.setImageResource(R.drawable.un_favorite_icon);
                }
            }else {
                addToFavoriteContainer.setVisibility(View.GONE);
            }

            if(checkoutPageConfigModel.getSetting().isDisplayFee()){
                containerFee.setVisibility(View.VISIBLE);
            }else {
                ViewGroup.LayoutParams layoutParams=textBankName.getLayoutParams();
                layoutParams.width=ViewGroup.LayoutParams.WRAP_CONTENT;
                layoutParams.height=ViewGroup.LayoutParams.MATCH_PARENT;

                textBankName.setLayoutParams(layoutParams);
                textBankName.setGravity(Gravity.CENTER);

                containerFee.setVisibility(View.GONE);
            }
        }
    }

    public interface PaymentMethodClickListener{
       void OnItemPaymentMethodClick(BankPaymentMethodItemModel id);
    }

}
