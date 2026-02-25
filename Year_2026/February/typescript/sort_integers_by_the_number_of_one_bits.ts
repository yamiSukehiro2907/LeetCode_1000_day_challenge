function sortByBits(arr: number[]): number[] {
    for (let i = 0; i < arr.length; i++) arr[i] += 10001 * getBitCount(arr[i]);
    quickSort(arr, 0, arr.length - 1);
    for (let i = 0; i < arr.length; i++) arr[i] %= 10001;
    return arr;
}

function getBitCount(num: number): number {
    let setBitCount = 0;
    let position = 31;
    while (position >= 0) {
        if (((num >> position) & 1) == 1) setBitCount++;
        position--;
    }
    return setBitCount;
}

function quickSort(arr: number[], start: number, end: number): void {
    if (start >= end) return;
    let part = partition(arr, start - 1, end + 1);
    quickSort(arr, start, part);
    quickSort(arr, part + 1, end);
}

function partition(arr: number[], left: number, right: number): number {
    let mid = (left + right) >> 1;
    let current = getPivot(arr[left + 1], arr[mid], arr[right - 1]);
    let temp = 0;
    while (true) {
        do {
            left++;
        } while (arr[left] < current);
        do {
            right--;
        } while (arr[right] > current);
        if (left >= right) return right;
        temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}

function getPivot(a: number, b: number, c: number): number {
    if ((a >= b) !== (a >= c)) return a;
    if ((a >= b) !== (c >= b)) return b;
    return c;
}

export default sortByBits;