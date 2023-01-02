package br.edu.linuquiz.model;

public class User {
    private String _email, _password, _name, _nickname, _icon;
    public User(String name, String nickname, String password, String email, String icon) {
        _email = email; _name = name; _password = password; _icon = icon; _nickname = nickname;
    }
    public User(){}

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_nickname() {
        return _nickname;
    }

    public void set_nickname(String _nickname) {
        this._nickname = _nickname;
    }

    public String get_icon() {
        return _icon;
    }

    public void set_icon(String _icon) {
        this._icon = _icon;
    }
}
