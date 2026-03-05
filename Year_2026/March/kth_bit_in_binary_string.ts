function findKthBit(n: number, k: number): string {
    if (n == 1) return '0';
    const totalChars = (1 << n) - 1;
    const left = totalChars >> 1;
    if (k <= left) return findKthBit(n - 1, k);
    if (k == left + 1) return '1';
    return findKthBit(n - 1, totalChars - k + 1) === '1' ? '0' : '1';
};