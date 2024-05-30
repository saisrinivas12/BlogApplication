package com.example.BlogApplication.entities;



import javax.persistence.*;

@Entity
@Table(name="IMAGE_DATA")
public class ImageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="IMAGE_NAME")
    private String imageName;

    @Column(name="IMAGE_TYPE")
    private String imageType;

    @Column(name="image_data")
    private byte[] bytes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
