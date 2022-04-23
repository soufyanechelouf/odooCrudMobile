package com.example.odooapp.Model;

public class Book1 {


        private int id;
        private String name, author;


        public Book1() {
        }

        public Book1(String name, String author) {
            this.name = name;
            this.author = author;
        }

        public Book1(int id, String name, String author) {
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



    }



