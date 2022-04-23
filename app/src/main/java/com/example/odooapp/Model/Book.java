package com.example.odooapp.Model;

public class Book {


        private int id;
        private String name, author,ref;


        public Book() {
        }

        public Book(String name, String author,String ref) {
            this.name = name;
            this.author = author;
            this.ref = ref;
        }

        public Book(int id, String name, String author) {
            this.id = id;
            this.name = name;
            this.author = author;

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

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}



