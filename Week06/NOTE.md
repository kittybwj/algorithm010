## 第六周学习总结
### 第十二课：动态规划

#### 一、回顾

**递归代码模板**

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

**分治代码模板**

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

#### 二、解决动态规划问题的思路

1. 拒绝人肉递归！
2. 找到最近最简方法，将其拆解成可重复的解决的方法
3. 数学归纳法思维。类似于放鞭炮:要证明一串爆竹能够爆炸，就要证明能点燃第二个爆竹且点燃前一个之后后一个就能被点燃。
4. 本质:寻找重复性——>计算机指令

#### 三、动态规划（Dynamic Programming）

1、关键点

* 动态规划和递归或者分治没有根本上的区别，关键看有无最优子结构。
* 共性：找到重复子问题。
* 差异性：最优子结构，中途可以淘汰次优解，只保存最优解。

2、三部曲

* 最优子结构 opt[n] = best_of(opt[n-1],opt[n-2]...)
* 存储中间状态 opt[i]
* DP方程（状态转移公式）