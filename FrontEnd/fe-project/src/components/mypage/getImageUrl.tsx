// 이미지 주소 동적으로 로딩하기 위한 컴포넌트

export default function getImageUrl(url: String){
    // return new URL("profile" + url + ".png", import.meta.env.VITE_LOCAL_ASSET_URL).href;
    return new URL("profile" + url + ".png", import.meta.env.VITE_SERVER_ASSET_URL).href;
}