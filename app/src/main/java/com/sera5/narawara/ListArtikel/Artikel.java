package com.sera5.narawara.ListArtikel;

public class Artikel {
    private String id, title, img_url, description, short_description, publish_date, update_date, view, author, url;

    public Artikel(String id, String title, String img_url, String description, String publish_date, String update_date, String author, String view, String url) {
        this.id = id;
        this.title = title;
        this.img_url = img_url;

        if(description!=null)
        this.description = description.replaceAll("<.*?>", "");
        else this.description = "";

        this.short_description = this.description.length() < 100 ? this.description : this.description.substring(0, 100);
        this.author=author;
        this.publish_date=publish_date;
        this.url=url;
        this.update_date = url;
        this.view = view;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getShort_description() {
        return short_description;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public String getView() {
        return view;
    }

    public String getUpdate_date() {
        return update_date;
    }

   public String getAuthor() {
        return author;
    }

    public String getImg() {
        return img_url;
    }


}
