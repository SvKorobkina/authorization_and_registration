package Registration;

public class Shop{
    private static int id;
    private String name; // наименование
    private ProdEnum type; // тип
    private String color; // цвет
    private int size; // размер
    private float price;

    Shop(int id, String name, String color, ProdEnum type,int size,float price)
    {
        this.id=id;
        this.name = name;
        this.color = color;
        this.type = type;
        this.size=size;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProdEnum getType() {
        return type;
    }

    public void setType(ProdEnum type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
