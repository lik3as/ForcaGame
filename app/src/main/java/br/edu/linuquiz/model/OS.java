package br.edu.linuquiz.model;

public class OS {
    public final String pkg_manager;
    public final String pkg_extension;
    public final String list = "ls";
    public final String bootloader = "grub";


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
