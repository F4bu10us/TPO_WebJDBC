package mindhunter.TPO.Servlets;


import java.util.ArrayList;

class Model {
     private static ArrayList<String> names;
     private static ArrayList<String> contexts;
     Model(){
          names = new ArrayList<>();
          contexts = new ArrayList<>();
     }

     void addContent(String name, String cont, int c){
          names.add(c,name);
          contexts.add(c,cont);
     }
     ArrayList<String> getNames(){return names;}
     ArrayList<String> getContexts(){return contexts;}
}
