package br.edu.forcagame.model;

public class User {
    private String password, name, nickname, email, icon;
    private int score;

    public User(){
    }

    public User(String password, String name, String nickname, String email, String icon) {
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.icon = icon;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getIcon() {
        return icon;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
