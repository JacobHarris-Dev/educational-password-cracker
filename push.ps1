# Navigate to your project directory (change the path to your repo)
cd "C:\Users\jacob\OneDrive\Desktop\Coding\Coding Projects\passwordcracker"

# Add all changes (new, modified, deleted files)
git add .

# Commit changes with a message (you can customize the message or prompt for it)
$commitMessage = Read-Host "Enter commit message"
git commit -m "$commitMessage"

# Push to the default remote (usually origin) and branch (usually main or master)
git push