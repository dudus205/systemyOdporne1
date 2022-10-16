#include <stdio.h>
#include <errno.h>

void readFile(char *file_path);

void readLines(FILE *file);

void printErrorMessage();

int main(int argc, char **argv) {
    if (argc < 2) {
        printf("No arguments were passed!\n");
        return -1;
    }
    readFile(argv[1]);

    return 0;
}

void readFile(char *file_path) {
    printf("File path: %s\n", file_path);
    FILE *file = fopen(file_path, "r");

    if (errno != 0) {
        printErrorMessage();
    } else {
        readLines(file);
    }
}

void printErrorMessage() {
    if (errno == ENOENT) {
        printf("File does not exist!\n");
    } else if (errno == EACCES) {
        printf("Permissions to file are denied!\n");
    } else {
        printf("errno error number: %d!\n", errno);
    }
}

void readLines(FILE *file) {
    int c;

    if (file) {
        while ((c = getc(file)) != EOF) {
            putchar(c);
        }
        fclose(file);
        printf("File has been read successfully!\n");
    }
}
