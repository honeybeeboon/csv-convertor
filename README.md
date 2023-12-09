# csv-convertor

input csv
```
key,col_1,col_2
key1,value1,value2
key1,value2,value3
key2,value3,value4
```

output csv
```
key1,"[{\"col_1\":\"value1\",\"col_2\":\"value2\"},{\"col_1\":\"value2\",\"col_2\":\"value3\"}]"
key2,"[{\"col_1\":\"value3\",\"col_2\":\"value4\"}]"
```
