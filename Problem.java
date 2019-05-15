package com.example.ali.fastmainappfinal;

public class Problem {
    public String Title;
    public String Problem_Description;
    public String Location;
    public int Picture_Id;
    public boolean status;
    public Problem(){
        Title=null;
        Problem_Description=null;
        Location=null;
        Picture_Id=0;
        status=false;
    }

    public Problem(String title, String problem_Description, String location, int picture_Id, boolean s) {
        Title = title;
        Problem_Description = problem_Description;
        Location = location;
        Picture_Id = picture_Id;
        status=s;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getProblem_Description() {
        return Problem_Description;
    }

    public void setProblem_Description(String problem_Description) {
        Problem_Description = problem_Description;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getPicture_Id() {
        return Picture_Id;
    }

    public void setPicture_Id(int picture_Id) {
        Picture_Id = picture_Id;
    }
    public boolean get_status()
    {
        return status;
    }
    public void setStatus(boolean s)
    {
        status=s;
    }
}

