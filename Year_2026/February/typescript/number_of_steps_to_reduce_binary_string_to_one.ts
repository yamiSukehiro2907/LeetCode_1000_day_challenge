function numSteps(s: string): number {
    let steps = 0;
    let carry = 0;
    const n = s.length;
    for (let i = n - 1; i > 0; i--) {
        const bit = parseInt(s[i]) + carry;
        if (bit === 1) {
            carry = 1;
            steps++;
        }
        steps++;
    }
    return steps + carry;
}