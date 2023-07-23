package classcopy;

public class Dog {

    @FieldCopyName(name = "name") // Similar to Hibernate @Table, @Column
    private String dogName;

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }
    
    
}
