package com.zsl.myapplication.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zsl.myapplication.R;
import com.zsl.myapplication.contact.Entity.CityEntity;
import com.zsl.myapplication.contact.Entity.ProviceEntity;

import java.util.List;

/**
 * Created by zsl on 3/3/16.
 */
public class CityListAdapter extends BaseExpandableListAdapter {


    private Context mContext;
    private List<ProviceEntity> mProvinceList;

    public CityListAdapter(Context mContext, List<ProviceEntity> mProvinceList) {
        this.mContext = mContext;
        this.mProvinceList = mProvinceList;
    }

    /**
     * Gets the number of groups.
     *
     * @return the number of groups
     */
    @Override
    public int getGroupCount() {
        return mProvinceList.size();
    }

    /**
     * Gets the number of children in a specified group.
     *
     * @param groupPosition the position of the group for which the children
     *                      count should be returned
     * @return the children count in the specified group
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        ProviceEntity cityEntity = mProvinceList.get(groupPosition);
        return cityEntity.getCityList().size();
    }

    /**
     * Gets the data associated with the given group.
     *
     * @param groupPosition the position of the group
     * @return the data child for the specified group
     */
    @Override
    public ProviceEntity getGroup(int groupPosition) {
        return mProvinceList.get(groupPosition);
    }

    /**
     * Gets the data associated with the given child within the given group.
     *
     * @param groupPosition the position of the group that the child resides in
     * @param childPosition the position of the child with respect to other
     *                      children in the group
     * @return the data of the child
     */
    @Override
    public CityEntity getChild(int groupPosition, int childPosition) {
        ProviceEntity provinceEntity = mProvinceList.get(groupPosition);
        return provinceEntity.getCityList().get(childPosition);
    }

    /**
     * Gets the ID for the group at the given position. This group ID must be
     * unique across groups. The combined ID (see
     * {@link #getCombinedGroupId(long)}) must be unique across ALL items
     * (groups and all children).
     *
     * @param groupPosition the position of the group for which the ID is wanted
     * @return the ID associated with the group
     */
    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    /**
     * Gets the ID for the given child within the given group. This ID must be
     * unique across all children within the group. The combined ID (see
     * {@link #getCombinedChildId(long, long)}) must be unique across ALL items
     * (groups and all children).
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child within the group for which
     *                      the ID is wanted
     * @return the ID associated with the child
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    /**
     * Indicates whether the child and group IDs are stable across changes to the
     * underlying data.
     *
     * @return whether or not the same ID always refers to the same object
     * @see Adapter#hasStableIds()
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_contact_group_layout,null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.privinceName = (TextView) convertView.findViewById(R.id.province_name);
            convertView.setTag(groupViewHolder);
            convertView.setMinimumHeight(60);
        }else{
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        ProviceEntity proviceEntity = mProvinceList.get(groupPosition);
        groupViewHolder.privinceName.setText(proviceEntity.getName()+"("+proviceEntity.getCityList().size()+")");
        return convertView;
    }

     static class GroupViewHolder{
        static TextView privinceName;
    }



    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ProviceEntity proviceEntity = mProvinceList.get(groupPosition);
        CityEntity cityEntity = proviceEntity.getCityList().get(childPosition);
        ChildViewHolder viewHodler;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_contact_child_layout,null);
            viewHodler = new ChildViewHolder();
            viewHodler.cityImageView = (ImageView) convertView.findViewById(R.id.city_icon);
            viewHodler.cityName = (TextView)convertView.findViewById(R.id.city_name);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ChildViewHolder) convertView.getTag();
        }
        viewHodler.cityImageView.setBackgroundResource(cityEntity.getIconId());
        viewHodler.cityName.setText(cityEntity.getName());
        return convertView;
    }

    static class ChildViewHolder{
        static ImageView cityImageView;
        static TextView cityName;
    }

    /**
     * Whether the child at the specified position is selectable.
     *
     * @param groupPosition the position of the group that contains the child
     * @param childPosition the position of the child within the group
     * @return whether the child is selectable.
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
