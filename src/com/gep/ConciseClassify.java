/**
 * 
 */
package com.gep;

/**
 * @author shenzhan
 *
 */
public class ConciseClassify extends FitnessFunction {

	/**
	 * ��ģ�ͷ��෨
	 * ��SenSep�Ļ����ϼ���ͷ� 
	 * �ǵĸ�����Ӽ��
	 */
	public ConciseClassify() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GetFitness(Population Pop, double[][] Data, double[] Fitness) {
		int nRow = Data.length;
		int nCol = Data[0].length;
	
		int i, j, k;

		for (i = 0; i < Pop.Size; ++i) {
			int tp = 0, fp = 0, tn = 0, fn = 0;
			for (j = 0; j < nRow; ++j) {
				double dValue = super.Exp.GetValue(Pop.Get(i), Data[j]);
				Pop.Get(i).Value=dValue;
				// ������ 0 ��
				if (Data[j][nCol-1] == 1) {
					if (dValue < 0) {
						tp++;
					} else {
						fp++;
					}
				} else if (Data[j][nCol-1] == 2) {
					if (dValue >= 0) {
						tn++;
					} else {
						fn++;
					}
				}

			}
			
			double se,sp,ss;
			
			if((tp+fn)<0.1){
				se = 0;
			}
			else
			    se = tp/(double)(tp+fn);//������
			
			if((tn+fp)<0.1){
				sp = 0;
			} else {
				sp = tn/(double)(tn+fp);//��Ч��
			}
			ss = se*sp;//����/��Ч��
			
			Expression Exp=new Expression();
			double dValidLen=Exp.GetIndivValidLen(Pop.Get(i));
			double dIndivLen=Pop.Get(i).Chrom.size();
			
			ss=ss*1000-(dValidLen/dIndivLen)*10 ;  //����ͷ�����
			
			Pop.Get(i).Fitness = ss;
			Fitness[i] = ss;
		}
		
	}

}
