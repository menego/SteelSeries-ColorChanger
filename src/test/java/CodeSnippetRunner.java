
public class CodeSnippetRunner {

    public static void main(String[] args) {

        try {

            System.out.println(System.getenv());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
