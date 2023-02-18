package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if (this.password.equals(oldPassword)){
            if(!checkString(newPassword)){
                return;
//                System.out.println("it dose't contain following condition \n " +
//                        "1. It contains at least 8 characters\n" +
//                        "2. It contains at least one uppercase letter\n" +
//                        "3. It contains at least one lowercase letter\n" +
//                        "4. It contains at least one digit\n" +
//                        "5. It contains at least one special character");
            }else{
                // change the password
                this.password = newPassword;
            }

        }else {

            //System.out.println("Wrong Current Pasword");
        }
    }
    public boolean checkString(String s){
        if(s.length()<8) return false;
        boolean uppercaseFlag = false;
        boolean lowercaseFlag = false;
        boolean digitflag = false;
        boolean specialcharFlag = false;
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if (Character.isUpperCase(c)){
                uppercaseFlag = true;
            }
            if (Character.isLowerCase(c)){
                lowercaseFlag = true;
            }if (Character.isDigit(c)){
                digitflag = true;
            }
            // using character class
            if(!Character.isDigit(c) && !Character.isLetter(c) && !Character.isWhitespace(c)){
                specialcharFlag = true;
            }
            if (uppercaseFlag && lowercaseFlag && digitflag && specialcharFlag){
                return true;
            }
        }
        return false;
    }
}
