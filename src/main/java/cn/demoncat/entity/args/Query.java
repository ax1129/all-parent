package cn.demoncat.entity.args;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.type.Alias;

@Alias("Query")
public class Query<E,I extends Number> {

	// 记录总数
	private I countNum;
	// 当前页
	private I pageNow;
	// 起始下标
	private I startIndex;
	// 每页记录数
	private Integer pageSize = 10;
	// 总页数
	private I pageNum;
	// 排序字段和方式(asc升序，desc降序)
	private Map<String,String> orders = new HashMap<String,String>();	
	// 联合查询(属性值为比较值，比较方式需要手工改动)
	private E pojo;
	// 封装对象的集合
	private List<E> pojos = new ArrayList<E>();
	// 封装批量执行的ID
	private List<I> ids= new ArrayList<I> ();
		
	
	
	
	
	public I getCountNum() {
		return countNum;
	}
	public void setCountNum(I countNum) {
		this.countNum = countNum;
	}
	@SuppressWarnings("unchecked")
	public Number getPageNow() {
		if(pageNow == null){
			if("Long".equals(pageNow.getClass().getSimpleName())){
				pageNow = (I) Long.valueOf(1);
			}else if("Integer".equals(pageNow.getClass().getSimpleName())){
				pageNow = (I) Integer.valueOf(1);
			}
		}
		return pageNow;
	}
	public void setPageNow(I pageNow) {
		this.pageNow = pageNow;
	}
	// 当前页起始记录的下标
	@SuppressWarnings("unchecked")
	public I getStartIndex() {
		if(startIndex == null){
			if("Long".equals(startIndex.getClass().getSimpleName())){
				startIndex = (I)Long.valueOf((startIndex.longValue() - 1)*pageSize);
			}else if("Integer".equals(startIndex.getClass().getSimpleName())){
				startIndex = (I)Integer.valueOf((startIndex.intValue() - 1)*pageSize);
			}
		}
		return startIndex;
	}
	public E getPojo() {
		return pojo;
	}
	public void setPojo(E pojo) {
		this.pojo = pojo;
	}
	public List<E> getPojos() {
		return pojos;
	}
	public void setPojos(List<E> pojos) {
		this.pojos = pojos;
	}
	public List<I> getIds() {
		return ids;
	}
	public void setIds(List<I> ids) {
		this.ids = ids;
	}
	public void setStartIndex(I startIndex){
		this.startIndex = startIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	// 总页数
	@SuppressWarnings("unchecked")
	public I getPageNum() {
		if(pageNum == null){
			if("Long".equals(pageNum.getClass().getSimpleName())){
				if((countNum.longValue() % pageSize) ==0){
					pageNum = (I) Long.valueOf(countNum.longValue()/pageSize);
				}else{
					pageNum = (I) Long.valueOf(countNum.longValue()/pageSize+1);
				}	
			}else if("Integer".equals(pageNum.getClass().getSimpleName())){
				if((countNum.intValue() % pageSize) ==0){
					pageNum = (I) Integer.valueOf(countNum.intValue()/pageSize);
				}else{
					pageNum = (I) Integer.valueOf(countNum.intValue()/pageSize+1);
				}
			}
		}
		return pageNum;
	}
	public void setPageNum(I pageNum){
		this.pageNum = pageNum;
	}
	public Map<String, String> getOrders() {
		return orders;
	}
	public void setOrders(Map<String, String> orders) {
		this.orders = orders;
	}
}
