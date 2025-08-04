import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class VirtualenvSetup {

    public static void main(String[] args) {
        System.out.println("Starting Python virtual environment setup...");

        try {
            // Upgrade pip, setuptools, wheel
            runCommand("python3 -m pip install --upgrade pip setuptools wheel");

            // Install virtualenvwrapper
            runCommand("python3 -m pip install --upgrade virtualenvwrapper --upgrade-strategy=eager");

            System.out.println("\n*** Python packages installed successfully! ***\n");

            // Instructions to the user
            System.out.println("*** You may now use virtualenv commands in your shell. ***");
            System.out.println("\nvirtualenv commands:");
            System.out.println("  *  mkvirtualenv [ENV_NAME]         - Create a Python virtual environment");
            System.out.println("  *  deactivate                      - Exit the current virtual environment");
            System.out.println("  *  workon [ENV_NAME]               - Enter an existing virtual environment");
            System.out.println("  *  lsvirtualenv / workon           - List all virtual environments");
            System.out.println("  *  rmvirtualenv [ENV_NAME]         - Delete a virtual environment");

            System.out.println("\nExample:");
            System.out.println("      mkvirtualenv seleniumbase");
            System.out.println("      mkvirtualenv seleniumbase --python=/usr/bin/python3");

            System.out.println("\nNOTE: To make virtualenvwrapper available, add this to your shell config (e.g., ~/.bashrc or ~/.zshrc):");
            System.out.println("      export WORKON_HOME=$HOME/.virtualenvs");
            System.out.println("      source $(which virtualenvwrapper.sh)");

        } catch (IOException | InterruptedException e) {
            System.err.println("Error during setup: " + e.getMessage());
        }
    }

    private static void runCommand(String command) throws IOException, InterruptedException {
        System.out.println("Running: " + command);
        ProcessBuilder builder = new ProcessBuilder("bash", "-c", command);
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
