package 设计模式.适配器模式;

// 中锋
public class Center extends Player{
    public Center(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println("中锋" + name + "进攻");
    }

    @Override
    public void defense() {
        System.out.println("中锋" + name + "防守");
    }
}
