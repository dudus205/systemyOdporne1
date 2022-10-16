#include <iostream>
#include <fstream>

void readFile(std::fstream &file);

void readLines(std::fstream &file);

int validateFile(std::fstream &file);

int main(int argc, char **argv) {
    if (argc < 2) {
        std::cout << "No arguments were passed!" << std::endl;
    }
    std::fstream file;
    char *filePath = argv[1];
    std::cout << "File path: " << filePath << std::endl;
    file.open(filePath, std::ios::in);

    if (validateFile(file)) {
        return 1;
    }
    readFile(file);
    file.close();

    return 0;
}

int validateFile(std::fstream &file) {
    if (!file.is_open()) {
        std::cout << "File does not exist!" << std::endl;
        return 1;
    } else if (!file.good()) {
        std::cout << "Permissions to file are not allowed!" << std::endl;
        return 1;
    }
    return 0;
}

void readFile(std::fstream &file) {
    readLines(file);
    std::cout << "File has been read successfully!" << std::endl;
}

void readLines(std::fstream &file) {
    char ch;

    while (true) {
        file >> ch;

        if (file.eof()) {
            break;
        }
    }
}
