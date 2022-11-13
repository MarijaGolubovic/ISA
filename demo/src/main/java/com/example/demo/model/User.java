package com.example.demo.model;

import com.example.demo.model.enumerations.Gender;
import com.example.demo.model.enumerations.UserCategory;
import com.example.demo.model.enumerations.UserStatus;
import com.example.demo.model.enumerations.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "`User`")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;
    private String phoneNumber;
    private String URN; //JMBG
    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender;
    private String profession;
    private String infoAboutInstitution;
    @Enumerated(EnumType.STRING)
    @Column(name="userType")
    private UserType userType;
    @Enumerated(EnumType.STRING)
    @Column(name="userStatus")
    private UserStatus userStatus;
    private int pointsNum;
    private int strikesNum;
    @Enumerated(EnumType.STRING)
    @Column(name="userCategory")
    private UserCategory userCategory;



    @ManyToOne
    @JoinColumn(name = "bloodBank_id")
    private BloodBank bloodBank;

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }

    public User() {}

    public User(Long id, String email, String password, String name, String surname, Address address, String phoneNumber, String URN, Gender gender, String profession, String infoAboutInstitution, UserType userType, UserStatus userStatus, int pointsNum, int strikesNum, BloodBank bloodBank) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.URN = URN;
        this.gender = gender;
        this.profession = profession;
        this.infoAboutInstitution = infoAboutInstitution;
        this.userType = userType;
        this.userStatus = userStatus;
        this.pointsNum = pointsNum;
        this.strikesNum = strikesNum;
        this.bloodBank = bloodBank;
        this.userCategory = UserCategory.REGULAR;
        }


    public User(String email, String password, String name, String surname, Address address, String phoneNumber, String URN, Gender gender, String profession, String infoAboutInstitution, UserType userType, UserStatus userStatus, int pointsNum, int strikesNum, BloodBank bloodBank) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.URN = URN;
        this.gender = gender;
        this.profession = profession;
        this.infoAboutInstitution = infoAboutInstitution;
        this.userType = userType;
        this.userStatus = userStatus;
        this.pointsNum = pointsNum;
        this.strikesNum = strikesNum;
        this.bloodBank = bloodBank;
        this.userCategory = userCategory;
        this.userCategory = UserCategory.REGULAR;
    }
    
    public UserCategory getUserCategory() {
		return userCategory;
	}

	public void setUserCategory(UserCategory userCategory) {
		this.userCategory = userCategory;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address adress) {
        this.address = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getURN() {
        return URN;
    }

    public void setURN(String URN) {
        this.URN = URN;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getInfoAboutInstitution() {
        return infoAboutInstitution;
    }

    public void setInfoAboutInstitution(String infoAboutInstitution) {
        this.infoAboutInstitution = infoAboutInstitution;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public int getPointsNum() {
        return pointsNum;
    }

    public void setPointsNum(int pointsNum) {
        this.pointsNum = pointsNum;
    }

    public int getStrikesNum() {
        return strikesNum;
    }

    public void setStrikesNum(int strikesNum) {
        this.strikesNum = strikesNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", URN='" + URN + '\'' +
                ", gender=" + gender +
                ", profession='" + profession + '\'' +
                ", infoAboutInstitution='" + infoAboutInstitution + '\'' +
                ", userType=" + userType +
                ", userStatus=" + userStatus +
                ", pointsNum=" + pointsNum +
                ", strikesNum=" + strikesNum +
                '}';
    }
}
