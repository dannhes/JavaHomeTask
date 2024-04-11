package expression.generic;


public class TypeBool implements Types<Boolean> {
    public Boolean add(Boolean a, Boolean b) {
        return a || b;
    }
    public Boolean subtract(Boolean a,Boolean b){
        return a ^ b;
    }
    public Boolean multiply(Boolean a,Boolean b){
        return a && b;
    }

    public Boolean divide(Boolean a,Boolean b){
        if (!b){
            return null;
        }else {
            return a;
        }
    }

    public Boolean negate(Boolean a){
        return a;
    }

    public Boolean count(Boolean a){
        if (a){
            return true;
        }
        return false;
    }

    public Boolean min(Boolean a,Boolean b){
        return a && b;
    }
    public Boolean max(Boolean a, Boolean b){
        return a || b;
    }


    public Boolean cast(int a){
        return a!=0;
    }

    public Boolean parse(String s){
        return Integer.parseInt(s)!=0;
    }


}
