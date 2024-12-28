#!/bin/bash

# Update script that automatically increases the MAJOR, MINOR or PATCH iteration 
# and updates the version on its dependants.

library=gengolex

# Extract the current version
version=$(grep -oP '<version>\K[0-9\.]+(?=</version>)' pom.xml | head -n 1) 

# Parse the version
IFS='.' read -r MAJOR MINOR PATCH <<< "$version"

iteration=$1

case "$iteration" in 
	patch)
		PATCH=$((PATCH + 1))
		;;
	minor)
		MINOR=$((MINOR + 1))
		PATCH=0
		;;
	major)
		MAJOR=$((MAJOR + 1))
		MINOR=0
		PATCH=0
		;;
	*)
		echo "Invalid action. Usage $0 {patch|minor|major}"
		exit 1
		;;	
esac

new_version="${MAJOR}.${MINOR}.${PATCH}"

echo " "
echo "Change: $version -> $new_version"
echo " "

# Write changes to pom.xml
read -p "Proceed with changes? [Y/N]: " choice

case "$choice" in
	Y|y|yes)
		sed -i "s/<version>${version}<\/version>/<version>${new_version}<\/version>/" pom.xml
		;;
	N|n|no)
		echo "Quitting..."
		exit 0
		;;
	*)
		echo "Invalid choice. Quitting..."
		exit 1
		;;
esac


echo " "
echo " "
git diff pom.xml

echo " "
read -p "Do you wish to commit these changes to git? [Y/n] " git_choice

case "$git_choice" in 
	Y|y|yes)
		git add pom.xml
		git commit -m "Bump version to $new_version"
		git status
		;;
	*|n|N|no)
		echo "Ignoring git..."
		;;
esac


# Write the new version on the dependants
#
# API

echo " "
echo " "
echo " "
echo " "
echo "> WordView Api: "

cd ~/Repos/APIWordView
sed -i "s/<version>${version}<\/version>/<version>${new_version}<\/version>/" pom.xml

echo " "
echo " "
git diff pom.xml

echo " "
read -p "Do you wish to commit these changes to git? [Y/n] " git_choice

case "$git_choice" in 
	Y|y|yes)
		git add pom.xml
		git commit -m "Bump $library to $new_version"
		git status
		;;
	*|n|N|no)
		echo "Ignoring git..."
		;;
esac


# App

echo " "
echo " "
echo " "
echo " "
echo "> WordView App: "


cd ~/Repos/WordView-android
sed -i "s/${library} = \"${version}\"/${library} = \"${new_version}\"/" gradle/libs.versions.toml

echo " "
echo " "
git diff gradle/libs.versions.toml

echo " "
read -p "Do you wish to commit these changes to git? [Y/n] " git_choice

case "$git_choice" in 
	Y|y|yes)
		git add pom.xml
		git commit -m "Bump $library to $new_version"
		git status
		;;
	*|n|N|no)
		echo "Ignoring git..."
		;;
esac

echo " "
echo " "
echo "Done!"


