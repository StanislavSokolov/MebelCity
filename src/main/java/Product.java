public class Product{
    private int id;
    private String name;
    private String category;
    private String subcategory;
    private String description;
    private String manufacturer;
    private String manufacturerPartNumber;
    private String mainPhoto;
    private String photo1;
    private String photo2;
    private String photo3;
    private int price;
    private int priceDiscount;
    private int rating;
    private int ratingBusket;
    private int ratingOrder;
    private String teg1;
    private String teg2;
    private String teg3;
    private String quantity;
    private String total;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Product(int id, String name, String category, String description, String manufacturer, String manufacturerPartNumber, String mainPhoto, String photo1, String photo2, String photo3, int price, int priceDiscount, int rating, int ratingBusket, int ratingOrder, String teg1, String teg2, String teg3) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.manufacturer = manufacturer;
        this.manufacturerPartNumber = manufacturerPartNumber;
        this.mainPhoto = mainPhoto;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.price = price;
        this.priceDiscount = priceDiscount;
        this.rating = rating;
        this.ratingBusket = ratingBusket;
        this.ratingOrder = ratingOrder;
        this.teg1 = teg1;
        this.teg2 = teg2;
        this.teg3 = teg3;
    }

    public Product(String id, String name, String mainPhoto, String photo1, String photo2, String photo3, int price) {
        this.name = name;
        this.mainPhoto = mainPhoto;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeg1() {
        return teg1;
    }

    public void setTeg1(String teg1) {
        this.teg1 = teg1;
    }

    public String getTeg2() {
        return teg2;
    }

    public void setTeg2(String teg2) {
        this.teg2 = teg2;
    }

    public String getTeg3() {
        return teg3;
    }

    public void setTeg3(String teg3) {
        this.teg3 = teg3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturerPartNumber() {
        return manufacturerPartNumber;
    }

    public void setManufacturerPartNumber(String manufacturerPartNumber) {
        this.manufacturerPartNumber = manufacturerPartNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getPhoto3() {
        return photo3;
    }

    public void setPhoto3(String photo3) {
        this.photo3 = photo3;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(int priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public int getRatingBusket() {
        return ratingBusket;
    }

    public void setRatingBusket(int ratingBusket) {
        this.ratingBusket = ratingBusket;
    }

    public int getRatingOrder() {
        return ratingOrder;
    }

    public void setRatingOrder(int ratingOrder) {
        this.ratingOrder = ratingOrder;
    }
}


