cd com/java/zteam/ooad/Veloxcena

for file in *.pde;
do
        echo "Committing $file 🐱‍🏍"
        git add $file
        git commit -m "Commit $file ✅"
        git push
done