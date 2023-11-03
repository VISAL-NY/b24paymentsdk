package com.bill24.b24paymentsdk.helper;

public class ConvertColorHexa {
        public static String convertHex(String inputHex) {
            // Remove the '#' character if it exists
            if(inputHex==null || inputHex.isEmpty()){
                inputHex="#CCCCCC";
                return  inputHex;
            }
            if(inputHex.length()>8){
                inputHex = inputHex.replace("#", "");
                // Rearrange the characters
                String outputHex = inputHex.substring(6) + inputHex.substring(0, 6);

                // Add the '#' character back
                outputHex = "#" + outputHex;

                return outputHex;
            }else {
                return inputHex;
            }
        }

    public static String getFiftyPercentColor(String colorCode) {
          if(colorCode==null || colorCode.isEmpty()){
              colorCode="#80CCCCCC";
              return colorCode;
          }

        if (colorCode.length() < 8) {
            colorCode=colorCode.replace("#","");
            return "#" +"80" +colorCode ;
        } else {
            // Remove the last two characters and add "80" after #
            colorCode=colorCode.replace("#","");
            return "#" + "80"+ colorCode.substring(0, 6);
        }
    }
}
