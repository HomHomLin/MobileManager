package com.homen.mobilemanager.file.util;

import android.text.TextUtils;

import com.homen.mobilemanager.R;

import java.io.File;
import java.util.HashMap;

/**
 * Created by Linhh on 16/1/17.
 */
public class FileIconUtil {
    private static FileIconUtil mFileIconUtil;
    private HashMap<String,Integer> mFileIconMap;

    private void initFileiconUtil(){
        mFileIconMap.put("zip", R.mipmap.file_icon_zip);
        mFileIconMap.put("3gp", R.mipmap.file_icon_3gp);
        mFileIconMap.put("aac", R.mipmap.file_icon_aac);
        mFileIconMap.put("ac3", R.mipmap.file_icon_ac3);
        mFileIconMap.put("ai", R.mipmap.file_icon_ai);
        mFileIconMap.put("aif", R.mipmap.file_icon_aif);
        mFileIconMap.put("aifc", R.mipmap.file_icon_aifc);
        mFileIconMap.put("aiff", R.mipmap.file_icon_aiff);
        mFileIconMap.put("amr", R.mipmap.file_icon_amr);
        mFileIconMap.put("ani", R.mipmap.file_icon_ani);
        mFileIconMap.put("ape", R.mipmap.file_icon_ape);
        mFileIconMap.put("apk", R.mipmap.file_icon_apk);
        mFileIconMap.put("asf", R.mipmap.file_icon_asf);
        mFileIconMap.put("au", R.mipmap.file_icon_au);
        mFileIconMap.put("avi", R.mipmap.file_icon_avi);
        mFileIconMap.put("bat", R.mipmap.file_icon_bat);
        mFileIconMap.put("bin", R.mipmap.file_icon_bin);
        mFileIconMap.put("bmp", R.mipmap.file_icon_bmp);
        mFileIconMap.put("bup", R.mipmap.file_icon_bup);
        mFileIconMap.put("cab", R.mipmap.file_icon_cab);
        mFileIconMap.put("cal", R.mipmap.file_icon_cal);
        mFileIconMap.put("cat", R.mipmap.file_icon_cat);
        mFileIconMap.put("cur", R.mipmap.file_icon_cur);
        mFileIconMap.put("dat", R.mipmap.file_icon_dat);
        mFileIconMap.put("dcr", R.mipmap.file_icon_dcr);
        mFileIconMap.put("der", R.mipmap.file_icon_der);
        mFileIconMap.put("dic", R.mipmap.file_icon_dic);
        mFileIconMap.put("dll", R.mipmap.file_icon_dll);
        mFileIconMap.put("doc", R.mipmap.file_icon_doc);
        mFileIconMap.put("docx", R.mipmap.file_icon_docx);
        mFileIconMap.put("dvd", R.mipmap.file_icon_dvd);
        mFileIconMap.put("dwt", R.mipmap.file_icon_dwt);
        mFileIconMap.put("epub", R.mipmap.file_icon_epub);
        mFileIconMap.put("fla", R.mipmap.file_icon_fla);
        mFileIconMap.put("flac", R.mipmap.file_icon_flac);
        mFileIconMap.put("flv", R.mipmap.file_icon_flv);
        mFileIconMap.put("fon", R.mipmap.file_icon_fon);
        mFileIconMap.put("font", R.mipmap.file_icon_font);
        mFileIconMap.put("gif", R.mipmap.file_icon_gif);
        mFileIconMap.put("hlp", R.mipmap.file_icon_hlp);
        mFileIconMap.put("hst", R.mipmap.file_icon_hst);
        mFileIconMap.put("html", R.mipmap.file_icon_html);
        mFileIconMap.put("ico", R.mipmap.file_icon_ico);
        mFileIconMap.put("ifo", R.mipmap.file_icon_ifo);
        mFileIconMap.put("inf", R.mipmap.file_icon_inf);
        mFileIconMap.put("ini", R.mipmap.file_icon_ini);
        mFileIconMap.put("iso", R.mipmap.file_icon_iso);
        mFileIconMap.put("java", R.mipmap.file_icon_java);
        mFileIconMap.put("jif", R.mipmap.file_icon_jif);
        mFileIconMap.put("jpg", R.mipmap.file_icon_jpg);
        mFileIconMap.put("log", R.mipmap.file_icon_log);
        mFileIconMap.put("m4a", R.mipmap.file_icon_m4a);
        mFileIconMap.put("mid", R.mipmap.file_icon_mid);
        mFileIconMap.put("mkv", R.mipmap.file_icon_mkv);
        mFileIconMap.put("mmf", R.mipmap.file_icon_mmf);
        mFileIconMap.put("mmm", R.mipmap.file_icon_mmm);
        mFileIconMap.put("mod", R.mipmap.file_icon_mod);
        mFileIconMap.put("mov", R.mipmap.file_icon_mov);
        mFileIconMap.put("mp2", R.mipmap.file_icon_mp2);
        mFileIconMap.put("mp2v", R.mipmap.file_icon_mp2v);
        mFileIconMap.put("mp3", R.mipmap.file_icon_mp3);
        mFileIconMap.put("mp4", R.mipmap.file_icon_mp4);
        mFileIconMap.put("mpeg", R.mipmap.file_icon_mpeg);
        mFileIconMap.put("mpg", R.mipmap.file_icon_mpg);
        mFileIconMap.put("msp", R.mipmap.file_icon_msp);
        mFileIconMap.put("ogg", R.mipmap.file_icon_ogg);
        mFileIconMap.put("pdf", R.mipmap.file_icon_pdf);
        mFileIconMap.put("png", R.mipmap.file_icon_png);
        mFileIconMap.put("ppt", R.mipmap.file_icon_ppt);
        mFileIconMap.put("pptx", R.mipmap.file_icon_pptx);
        mFileIconMap.put("psd", R.mipmap.file_icon_psd);
        mFileIconMap.put("ra", R.mipmap.file_icon_ra);
        mFileIconMap.put("ram", R.mipmap.file_icon_ram);
        mFileIconMap.put("rar", R.mipmap.file_icon_rar);
        mFileIconMap.put("reg", R.mipmap.file_icon_reg);
        mFileIconMap.put("rm", R.mipmap.file_icon_rm);
        mFileIconMap.put("rmvb", R.mipmap.file_icon_rmvb);
        mFileIconMap.put("rtf", R.mipmap.file_icon_rtf);
        mFileIconMap.put("swf", R.mipmap.file_icon_swf);
        mFileIconMap.put("tiff", R.mipmap.file_icon_tiff);
        mFileIconMap.put("tlb", R.mipmap.file_icon_tlb);
        mFileIconMap.put("ttf", R.mipmap.file_icon_ttf);
        mFileIconMap.put("txt", R.mipmap.file_icon_txt);
        mFileIconMap.put("vob", R.mipmap.file_icon_vob);
        mFileIconMap.put("wav", R.mipmap.file_icon_wav);
        mFileIconMap.put("wmv", R.mipmap.file_icon_wmv);
        mFileIconMap.put("wpl", R.mipmap.file_icon_wpl);
        mFileIconMap.put("wri", R.mipmap.file_icon_wri);
        mFileIconMap.put("xls", R.mipmap.file_icon_xls);
        mFileIconMap.put("xlsx", R.mipmap.file_icon_xlsx);
        mFileIconMap.put("xml", R.mipmap.file_icon_xml);
        mFileIconMap.put("xsl", R.mipmap.file_icon_xsl);
    }

    public FileIconUtil(){
        if(mFileIconMap == null) {
            mFileIconMap = new HashMap<>();
            initFileiconUtil();
        }
    }

    public static FileIconUtil getIntance(){
        if(mFileIconUtil == null){
            mFileIconUtil = new FileIconUtil();
        }
        return mFileIconUtil;
    }

    public int getFileicon(String path){
        String ext = FileUtil.getFileExt(path);
        if(!TextUtils.isEmpty(ext) && mFileIconMap != null){
            Integer icon = mFileIconMap.get(ext);
            if(icon != null){
                return icon;
            }
        }
        return R.mipmap.default_fileicon;
    }
}
