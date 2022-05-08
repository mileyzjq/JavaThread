public class Lambda {
    // static inner class
    static class Like2 implements Like {
        @Override
        public void lambda(int a, int b) {
            System.out.println(String.format("I like number: %d", a + b));
        }
    }

    public static void main(String[] args) {
        Like like;

        like = new Like2();
        like.lambda(1,2);

        // local inner class
        class Like3 implements Like {
            @Override
            public void lambda(int a, int b) {
                System.out.println(String.format("I like number: %d", a + b));
            }
        }
        like = new Like3();
        like.lambda(3,4);

        // anonymous inner class(nested class)
        like = new Like() {
            @Override
            public void lambda(int a, int b) {
                System.out.println(String.format("I like number: %d", a + b));
            }
        };
        like.lambda(5,6);

        // lamda expression
        like = (a,b)->{
            System.out.println(String.format("I like number: %d", a + b));
        };
        like.lambda(9,10);
    }
}

interface Like {
    void lambda(int a, int b);
}
