package testthread;

public class TestLambda {

    // 3. 静态内部类
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("I like lambda2.");
        }
    }

    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        // 4. 局部内部类
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println("I like lambda3.");
            }
        }

        like = new Like3();
        like.lambda();

        // 5. 匿名内部类, 没有类的名称, 必须借助接口或者父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("I like lambda4.");
            }
        };
        like.lambda();

        // 6. 用lambda简化， 括号内是参数，箭头后是方法体
        like = () -> {
            System.out.println("I like lambda5.");
        };

        // 7. 如果方法体只有一行代码，可以省略大括号
        like = () -> System.out.println("I like lambda6.");
        like.lambda();

    }
}

// 1. 定义一个函数式接口，且只能有一个方法
interface ILike {
    void lambda();
}

// 2. 实现类
class Like implements ILike {
    @Override
    public void lambda() {
        System.out.println("I like lambda.");
    }
}