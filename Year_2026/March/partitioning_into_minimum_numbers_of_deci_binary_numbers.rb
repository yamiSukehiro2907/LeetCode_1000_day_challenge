def min_partitions(n)
  9.downto(1).each do |digit|
    return digit if n.include?(digit.to_s)
  end
  0
end