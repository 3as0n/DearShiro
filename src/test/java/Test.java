public class Test {
    public static void main(String[] args) {
        String commandTemplate = "java.lang.Runtime.getRuntime.exec(\"%s\")";
        String command = "whoami";
        System.out.println(String.format(commandTemplate, command));
    }
}
