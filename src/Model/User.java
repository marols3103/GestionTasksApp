package Model;

import javax.swing.text.StyledEditorKit;

public class User {
    private String name ;
    private String firstName;
    private String email;
    private String passWord;
    private Boolean estUser;

    public User(String name,String firstName,String email,String passWord,Boolean estUser){
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.passWord = passWord;
        this.estUser = estUser;

    }

    public String getEmail(){
        return email;
    }
    public String getPassWord(){
        return passWord;
    }

    public boolean login(String email,String passWord){
        if(this.email.equals(email) && this.passWord.equals(passWord)){
            return true;
        }else{
            return  false;
        }

    }


    public void SignUP(String name, String firstName, String email, String passWord){
        this.name  = name;

    }

}
