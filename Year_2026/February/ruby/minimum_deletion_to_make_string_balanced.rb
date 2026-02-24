def minimum_deletions(s)
   ans = 0
   count = 0
   index = 0
   while index < s.length do
      if s[index] == 'b'
        count += 1
      elsif count > 0
        ans += 1
        count -= 1
      end
      index += 1
   end
   return ans
end
