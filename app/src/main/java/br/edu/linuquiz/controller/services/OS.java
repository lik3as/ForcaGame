package br.edu.linuquiz.controller.services;

public class OS {
    final String pkg_manager;
    final String pkg_extension;
    final String bootloader = "grub";

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
}
