package sge.admin.controller;

public class CategoryVO {
   
	
   private int category_num; 
   private String category_name;     
   
   public CategoryVO() {}
   
   public CategoryVO(int category_num, String category_name) {
      
      this.category_num = category_num;
      this.category_name = category_name;
   }

   public int getCategory_num() {
      return category_num;
   }

   public void setCategory_num(int category_num) {
      this.category_num = category_num;
   }

   public String getCategory_name() {
      return category_name;
   }

   public void setCategory_name(String category_name) {
      this.category_name = category_name;
   }
   
}