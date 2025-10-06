package tokyslav.gui;

import tokyslav.FileTypes;

public class GetImagePath {
    public String getImagePath(FileTypes type) {
        String imgPath;
        switch (type) {
            case DIRECTORY:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Drive_Icon_New.png";
                break;
            case FILE:
                imgPath = "src\\main\\java\\tokyslav\\gui\\File_Icon_New.png";
                break;
            case DRIVE:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Drive_Start_Icon.png";
                break;
            case IMAGE:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Image_Icon_New.png";
                break;
            default:
                imgPath = "src\\main\\java\\tokyslav\\gui\\Other_Icon.png";
                break;
        }
        return imgPath;
    }
}
