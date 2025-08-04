import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class VirtualenvSetupWin {

    public static void main(String[] args) {
        System.out.println("Starting Python virtual environment setup for Windows...");

        try {
            runCommand("py -m pip install --upgrade pip --user");
            runCommand("py -m pip install --upgrade wheel --user");
            runCommand("py -m pip install virtualenvwrapper-win --force-reinstall --user");

            System.out.println();
            System.out.println("*** You may now use virtualenv commands in your command shell. ***\n");

            System.out.println("virtualenv commands:");
            System.out.println("  *  mkvirtualenv [ENV_NAME]         - Create a Python virtual environment");
            System.out.println("  *  deactivate                      - Exit the current virtual environment");
            System.out.println("  *  workon [ENV_NAME]               - Enter an existing virtual environment");
            System.out.println("  *  lsvirtualenv / workon           - List all virtual environments");
            System.out.println("  *  rmvirtualenv [ENV_NAME]         - Delete a virtual environment");

            System.out.println("\nExample:");
            System.out.println("      mkvirtualenv seleniumbase");
            System.out.println("      mkvirtualenv seleniumbase --python=[PATH_TO_PYTHON]");

        } catch (IOException | InterruptedException e) {
            System.err.println("❌ Error during setup: " + e.getMessage());
        }
    }

    private static void runCommand(String command) throws IOException, InterruptedException {
        System.out.println(">> Running: " + command);
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", command);
        Process process = builder.start();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Command failed with exit code " + exitCode);
        }
    }
}
