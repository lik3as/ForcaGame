package br.edu.linuquiz.model;

public class OS {
    public final String pkg_manager;
    public final String pkg_extension;
    public final String list = "ls";
    public final String bootloader = "grub";
    private String app_sound = "";
    private String app_net = "";

    public String getApp_sound() {
        return app_sound;
    }
    
    public String getApp_net() {
        return app_net;
    }


    public OS(String name){
        switch(name){
            case "arch":
                pkg_manager = "pacman";
                pkg_extension = ".tar.gz";
                break;
            case "fedora":
                pkg_manager = "dnf";
                pkg_extension = ".rpm";
                break;
            case "debian":
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
