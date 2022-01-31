package models;

import java.util.Date;

import javafx.scene.image.ImageView;

public class Coach {
	// Les caractéristiques de client
		private String first_name,
		               last_name,
		               email,
		               phone,
		               address,
		               type_sport;
		private ImageView image;
		private Date date_entry;
		private float salary;
		
		// Constructeur :
		public Coach(String first_name ,String last_name, String email ,String phone,String address,Date date_entry,ImageView image,String type_sport,float salary) {
			this.first_name=first_name;
			this.last_name=last_name;
			this.email=email;
			this.phone=phone;
			this.address=address;
			this.date_entry=date_entry;
			this.image=image;
			this.type_sport=type_sport;
			this.salary=salary;
		}
		// Get && Set
		public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public Date getDate_entry() {
			return date_entry;
		}
		public void setDate_entry(Date date_entry) {
			this.date_entry = date_entry;
		}
		public ImageView getImage() {
			return image;
		}
		public void setImage(ImageView image) {
			this.image = image;
		}
		public String getType_sport() {
			return type_sport;
		}
		public void setType_sport(String type_sport) {
			this.type_sport = type_sport;
		}
		public float getPayment() {
			return salary;
		}
		public void setPayment(float salary) {
			this.salary = salary;
		}
		
		

}
