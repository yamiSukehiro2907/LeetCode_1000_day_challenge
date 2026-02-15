def add_binary(a, b)
    ans = ""
    indexA = a.length - 1
    indexB = b.length - 1
    carry = 0
    while indexA >= 0 || indexB >= 0 || carry > 0 do
        carry += a[indexA].to_i if indexA >= 0
        carry += b[indexB].to_i if indexB >= 0
        ch = carry % 2
        ans << ch.to_s
        carry /= 2
        indexA -= 1
        indexB -= 1
    end
    return ans.reverse
end