#include <cassert>
#include <cstdint>
#include <iostream>

int32_t sbox[8][4][16] = {
    {
        {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
        {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
        {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
        {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
    }
};

int32_t substitute(int32_t byte, int32_t index) {
    int32_t row = ((byte & 0x20) >> 4) | (byte & 0x01);
    int32_t col = (byte & 0x1E) >> 1;
    return sbox[index][row][col];
}

int main() {
    std::string input = "B1B2B3B4B5B6B7B8";

    assert(input.size() == 16); // Input must be exactly 16 chars lenght

    std::string output;
    for (uint8_t i = 0; i < 8; i++) {
        int32_t byte = stoi(input.substr(2 * i, 2), nullptr, 16); // to_hex

        output += std::to_string(substitute(byte, i));
    }

    std::cout << "Result of substitution: " << output << std::endl;

    return 0;
}
