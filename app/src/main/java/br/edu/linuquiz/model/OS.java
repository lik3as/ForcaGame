package br.edu.linuquiz.model;

public class OS {
    public final String pkg_manager;
    public final String pkg_extension;
    public final String list = "ls";
    public final String bootloader = "grub";

    public String getApp_sound() {
        return app_sound;
    }

    public void setApp_sound(String app_sound) {
        this.app_sound = app_sound;
    }

    private String app_sound;
    private String app_net;

    public String getApp_net() {
        return app_net;
    }

    public void setApp_net(String app_net) {
        this.app_net = app_net;
    }

    public OS(String name){
        switch(name){
            case "Arch":
                pkg_manager = "pacman";
                pkg_extension = ".tar.gz";
                break;
            case "Fedora":
                pkg_manager = "dnf";
                pkg_extension = ".rpm";
                break;
            case "Debian":
            default:
                pkg_manager = "apt-get";
                pkg_extension = ".deb";
                break;
        }
    }
    OS(){
        pkg_extension = "";
        pkg_manager = "";
    }
}
