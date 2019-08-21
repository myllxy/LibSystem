package Bean;

/**
 * 每个页面的基础功能
 *
 * @author nsu_zk
 * @create 2019-08-21 13:52
 */
public interface Pagefunction {
    /**
     * 初始化
     */
    public void init();

    /**
     * 刷新
     */
    public void refresh();

    /**
     * 组件初始化
     */
    public void newcomponent();

    /**
     * 设置组件
     */
    public void setcomponent();

    /**
     * 添加组件到
     */
    public void addcomponentto();
}
