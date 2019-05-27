package mindhunter.TPO.Servlets;

class Logic {

    String query(String login, String password){
        return "Select A.RESNAME, A.RESCONTENT " +
                "from RESOURCE_TABLE A, USER_TABLE B\n" +
                ", USER_RESOURCE C WHERE B.IDUSER = C.IDUSER AND\n" +
                "A.IDRESOURCE=C.IDRESOURCE AND B.LOGIN = '" + login
                +"' AND B.USERPASSWORD = '" + password + "'";
    }
}
