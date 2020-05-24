import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.ToString;

public class TestGson {

    @ToString
    static class Hero {
        String name;
        String skill1;
        String skill2;
        String skill3;
        String skill4;
    }
    public static void main(String[] args) {
        // 把对象构造成 json 格式的字符串
        // 把 json 格式的字符串转换成对象
        Hero hero = new Hero();
        hero.name = "曹操";
        hero.skill1 = "三段跳";
        hero.skill2 = "剑气";
        hero.skill3 = "加攻击";
        hero.skill4 = "技能";

        Gson gson = new GsonBuilder().create();
        String jsonString = gson.toJson(hero);
        System.out.println(jsonString);


        Hero hero1 = gson.fromJson(jsonString, Hero.class);
        System.out.println(hero1);
    }
}
