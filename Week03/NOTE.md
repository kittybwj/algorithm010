## 第三周学习总结

### 一、第7课：泛型递归、树的递归

#### 1、递归（Recursion）

**1）递归代码模板（Java）背过！！！**

```

public void recur(int level, int param) {

	// 1、recursion terminater(递归终结条件)
	if (level > MAX_LEVEL) {
		// process result
		return;
	}
	
	// 2、process current logic（处理当前层逻辑）
	process(level, param);
	
	// 3、drill down （下探到下一层）
	recur(level+1, newParam);
	
	// 4、restore current status（清理当前层）
	
}

```

**2）思维要点**

* 不要人肉进行递归
* 找重复最近子问题
* 数学归纳法思维


### 二、第8课：分治、回溯

**分治、回溯 ——> 特殊的递归——>找重复性**

#### 1、分治（divide& conquer）

* divide ——> conquer ——> merge

* problem ——> sub-problem ——> sub-solution ——> solution

分治代码模板

```
public void divideConquer(problem, param1, param2) {
		// recursion terminater(递归终结条件)
		if (problem is null) {
				// process result
				return;
		}
		// prepare data (将大问题分成一个个子问题)
		data = prepareData(problem);
		subProblems = splitProblem(problem, data);

		// conquer subproblems（逐一解决子问题，获得子问题的结果）
		subResult1 = divideConquer(subProblems[1],p1,p2);
		subResult2 = divideConquer(subProblems[2],p1,p2);
		subResult3 = divideConquer(subProblems[3],p1,p2);

    // process and generate the final result（将subSolution组合成Solution）
    result = processResult(subResult1, subResult2, subResult3 ...)

    //restore current status（清理当前层）
}
```

#### 2、回溯（Backtracking）

* 用递归来实现

1）采用试错的思想，尝试分步解决一个问题

2）在分布过程中，如果发现现有不能满足，则取消上一步或上几部的计算

* 归去来兮







