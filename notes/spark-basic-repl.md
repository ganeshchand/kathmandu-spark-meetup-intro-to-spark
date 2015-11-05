#Spark Basics


##Using REPL


> val numRDD = sc.parallelize(1 to 10)

> val evenNumRDD = numRDD.filter(_ % 2 == 0)

> val oddNumRDD = numRDD.filter(_ % 2 != 0)

> numRDD.collect

> evenNumRDD.collect

> oddNumRDD.collect

> val unionRDD = evenNumRDD.union(oddNumRDD)

> unionRDD.collect

> val sortedUnionRDD = unionRDD.collect.sorted
