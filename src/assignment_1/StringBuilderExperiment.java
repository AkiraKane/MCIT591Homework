package assignment_1;

public class StringBuilderExperiment {
    public static void main(String[] args) {
        StringBuilder myName = new StringBuilder("Hezijian Xiao");
        System.out.println(myName);
        myName.delete(0, 9);
        System.out.println(myName);
        StringBuilder myNewName = new StringBuilder();

        myNewName.append(myName);
        myNewName.append(myName.reverse());
        System.out.println(myNewName);

    }
}
